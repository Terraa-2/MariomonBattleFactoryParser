package battlefactory.dto;

public class EffortValue {
	
	public static final String HP_NAME = "HP";
	public static final String ATK_NAME = "ATTACK";
	public static final String DEF_NAME = "DEFENSE";
	public static final String SPA_NAME = "SPECIAL_ATTACK";
	public static final String SPD_NAME = "SPECIAL_DEFENSE";
	public static final String SPE_NAME = "SPEED";
	
	public enum Stats {
		HP,ATTACK,DEFENSE,SPECIAL_ATTACK,SPECIAL_DEFENSE,SPEED
	}
	
	private Stats stat;
	private Integer value;
	
	public EffortValue(Stats stat, Integer value) {
		this.stat = stat;
		this.value = value;
	}
	
	public EffortValue(String statName, Integer value) {
		this.stat = Stats.valueOf(statName);
		this.value = value;
	}
	
	public Stats getStat() {
		return stat;
	}
	public void setStat(Stats stat) {
		this.stat = stat;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
}
