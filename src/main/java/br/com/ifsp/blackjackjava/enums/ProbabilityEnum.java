package br.com.ifsp.blackjackjava.enums;

public enum ProbabilityEnum {
	
    FIFTEEN(15, 0.95),
    SIXTEEN(16, 0.85),
    SEVENTEEN(17, 0.75),
    EIGHTEEN(18, 0.65),
    NINETEEN(19, 0.55),
    TWENTY(20, 0.45),
    NONE(0, 0);

    private final int score;
    private final double probability;

    ProbabilityEnum(int score, double probability) {
        this.score = score;
        this.probability = probability;
    }

    public int getScore() {
        return score;
    }

    public double getProbability() {
        return probability;
    }

    public static ProbabilityEnum fromScore(int score) {
    	if(score >= 21) {
    		return NONE;
    	}
        for (ProbabilityEnum probability : ProbabilityEnum.values()) {
            if (probability.getScore() == score) {
                return probability;
            }
        }
        return FIFTEEN;
    }
}
