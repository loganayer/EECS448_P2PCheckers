package com.groupone.p2pgame;

public class CheckerMove {
    private CheckerSquare start;
    private CheckerSquare end;

    public CheckerMove (CheckerSquare start, CheckerSquare end) {
	this.start = start;
	this.end = end;
    }

    public boolean isDoubleJump() {
	int dx = this.getEnd().getX() - getStart().getX();
	int dy = this.getEnd().getY() - getStart().getY();
	return dx == 2 && dy == 2;
    }

    public boolean isSingleJump() {
	int dx = this.getEnd().getX() - getStart().getX();
	int dy = this.getEnd().getY() - getStart().getY();
	return dx == 1 && dy == 1;
    }

    public CheckerSquare getStart () {
	return this.start;
    }

    public CheckerSquare getEnd () {
	return this.end;
    }
}
