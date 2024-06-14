package br.com.ifsp.blackjackjava.enums;

public enum GameStatusEnum {
	
	PLAYING(0),
	PLAYER_LOST(1),
	PLAYER_WON(2),
	PLAYER_STOPED(3),
	COM_STOPED(4),
	DRAW(5);
	
	private int status;

	GameStatusEnum(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

}
