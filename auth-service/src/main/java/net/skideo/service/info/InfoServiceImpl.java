package net.skideo.service.info;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import net.skideo.dto.AuthDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Info;
import net.skideo.repository.InfoRepository;
import net.skideo.security.JwtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {

    private final InfoRepository infoRepository;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean isPasswordCorrect(String rowPassword, String encodedPassword) {
        return encoder.matches(rowPassword, encodedPassword);
    }

    @Override
    public boolean isAuthExists(String login) {
        return infoRepository.existsByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Info findByLogin(String login) {
        return infoRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException("Auth not found")
        );
    }

    @Override
    public void updateLoginAndPassword(AuthDto authDto) {
        Info dbInfo = getCurrentInfo();

        if(StringUtils.isNotBlank(authDto.getLogin())) {
            dbInfo.setLogin(authDto.getLogin());
        }
        if(StringUtils.isNotBlank(authDto.getPassword())) {
            dbInfo.setPassword(encoder.encode(authDto.getPassword()));
        }

        infoRepository.save(dbInfo);
    }

    @Override
    public ResponseEntity<OAuth2AccessToken> generateToken(Map<String, String> parameters, String clientId) throws HttpRequestMethodNotSupportedException {
        JwtInfo jwtAuth = JwtInfo.authToJwtAuth(new Info(clientId));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtAuth,null,null);

        return tokenEndpoint.postAccessToken(authentication,parameters);
    }

    @Transactional(readOnly = true)
    public Info getCurrentInfo() {
        return findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}