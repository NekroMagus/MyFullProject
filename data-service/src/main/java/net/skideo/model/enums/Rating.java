package net.skideo.model.enums;

public enum Rating {

    ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5);

    Rating(int rating) {
        this.rating=rating;
    }

    int rating;

    public int getRating() {
        return rating;
    }
}
