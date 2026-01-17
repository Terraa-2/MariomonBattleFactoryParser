package battlefactory.dto;

import java.util.List;

public class CaptureSet {
	private String name;
	private String ability;
	private String item;
	private List<String> moves;
	private String nature;
	private List<EffortValue> evs;
	private String author;
	private Integer setNum;
	
	public CaptureSet() {
		super();
	}
	
	//We use sublist to remove any additional moves or evs that might be there by mistake
	public CaptureSet(String name, String ability, String item, List<String> moves, String nature, List<EffortValue> evs) {
		this.name = name;
		this.ability = ability;
		this.item = item;
		this.moves = moves.subList(0, 3);
		this.nature = nature;
		this.evs = evs.subList(0, 5);;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public List<String> getMoves() {
		return moves;
	}

	public void setMoves(List<String> moves) {
		this.moves = moves;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public List<EffortValue> getEvs() {
		return evs;
	}

	public void setEvs(List<EffortValue> evs) {
		this.evs = evs;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getSetNum() {
		return setNum;
	}

	public void setSetNum(Integer setNum) {
		this.setNum = setNum;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("[FRONTIER_MON_%s_%s_%s] = {", this.name,this.author,this.setNum));
		str.append(String.format(".species = SPECIES_%s,", this.name));
		str.append(String.format(".moves = {%s, %s, %s, %s},", this.moves.get(0),this.moves.get(1),this.moves.get(2),this.moves.get(3)));
		str.append(String.format(".heldItem = %s,", this.item));
		// EVS order: hp, atk, def, speed, spatk, spdef
		str.append(String.format(".ev = TRAINER_PARTY_EVS(0, 252, 0, 252, 0, 0),", this.evs.get(0),this.evs.get(1),this.evs.get(2),this.evs.get(3),this.evs.get(4),this.evs.get(5)));
		str.append(String.format(".nature = %s,", this.nature));
		str.append(String.format(".ability = %s,", this.ability));
		str.append(".ball = BALL_POKE");
		str.append("}\n");
		return str.toString();
	}
	
	
}
