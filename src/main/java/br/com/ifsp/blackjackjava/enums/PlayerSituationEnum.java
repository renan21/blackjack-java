package br.com.ifsp.blackjackjava.enums;

public enum PlayerSituationEnum {
	
	PLAYING(0),
	LOOSE(1),
	WIN(2),
	STOP(3);
	
	private int situation;

	PlayerSituationEnum(int situation) {
		this.situation = situation;
	}
	
	public int getSituation() {
		return this.situation;
	}

	PlayerSituationEnum valueOf(int situation2) {
		// TODO Auto-generated method stub
		return null;
	}


}
