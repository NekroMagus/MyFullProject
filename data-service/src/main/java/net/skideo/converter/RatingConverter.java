package net.skideo.converter;

import net.skideo.model.enums.Rating;
import javax.persistence.AttributeConverter;

public class RatingConverter implements AttributeConverter<Rating,Integer> {

    @Override
    public Integer convertToDatabaseColumn(Rating attribute) {
        return attribute.getRating();
    }

    @Override
    public Rating convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 1:
                return Rating.ONE;
            case 2:
                return Rating.TWO;
            case 3:
                return Rating.THREE;
            case 4:
                return Rating.FOUR;
        }
        return Rating.FIVE;
    }
}
