package net.skideo.service.user;

import net.skideo.dto.SearchDto;
import net.skideo.dto.projections.ProfileProjection;
import net.skideo.exception.UserNotFoundException;
import net.skideo.model.User;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SearchDto> search(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople,
                                  LeadingLeg leadingLeg, LocalDate dateOfBirth, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllByInfoCountryOrInfoRoleFootballOrHasAgentOrInfoRolePeopleOrLeadingLegOrBirthDate(country,roleFootball,agent,rolePeople,leadingLeg,dateOfBirth,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(
                UserNotFoundException::new
        );
    }
}
