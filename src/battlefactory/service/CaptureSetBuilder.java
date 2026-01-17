package battlefactory.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import battlefactory.dto.CaptureSet;
import battlefactory.dto.EffortValue;
import battlefactory.utils.BattleFactoryUtils;

public class CaptureSetBuilder {
	
	private static final String FILE_SEPARATOR = ",";
	private static final String MOVE_PREFIX = "MOVE_";
	private static final String ITEM_PREFIX = "ITEM_";
	private static final String NATURE_PREFIX = "NATURE_";
	private static final Integer CAPTURE_NAME_POSITION = 0;
	private static final Integer ABILITY_NUMBER_POSITION = 2;
	private static final Integer ITEM_POSITION = 3;
	private static final Integer FIRST_MOVE_POSITION = 4;
	private static final Integer LAST_MOVE_POSITION = 7;
	private static final Integer NATURE_POSITION = 9;
	private static final Integer HP_POSITION = 9;
	private static final Integer ATK_POSITION = 10;
	private static final Integer DEF_POSITION = 11;
	private static final Integer SPA_POSITION = 12;
	private static final Integer SPD_POSITION = 13;
	private static final Integer SPE_POSITION = 14;
	private static final String SET_AUTHOR = "TERRA";
	
	public static CaptureSet buildCaptureSet(String line) {
		return buildCaptureSet(line, 1);
	}
	
	public static CaptureSet buildCaptureSet(String line, int setNumber) {
		CaptureSet capSet = new CaptureSet();
		String[] fields = line.split(FILE_SEPARATOR);
		List<String> moves = new ArrayList<>();
		List<EffortValue> evs = new ArrayList<>();
		capSet.setName(BattleFactoryUtils.toUpperCaseNoSeparator(fields[CAPTURE_NAME_POSITION]));
		capSet.setAbility(BattleFactoryUtils.nullToString(fields[ABILITY_NUMBER_POSITION]));
		capSet.setItem(BattleFactoryUtils.toUpperCaseWithSeparator(ITEM_PREFIX+fields[ITEM_POSITION]));
		for(int i=FIRST_MOVE_POSITION;i<=LAST_MOVE_POSITION;i++) {
			moves.add(BattleFactoryUtils.toUpperCaseWithSeparator(MOVE_PREFIX+fields[i]));	
		}
		capSet.setMoves(moves);
		capSet.setNature(NATURE_PREFIX+BattleFactoryUtils.toUpperCaseNoSeparator(fields[NATURE_POSITION]));
		evs.add(new EffortValue(EffortValue.HP_NAME, Integer.valueOf(BattleFactoryUtils.nullToString(fields[HP_POSITION]))));
		evs.add(new EffortValue(EffortValue.ATK_NAME, Integer.valueOf(BattleFactoryUtils.nullToString(fields[ATK_POSITION]))));
		evs.add(new EffortValue(EffortValue.DEF_NAME, Integer.valueOf(BattleFactoryUtils.nullToString(fields[DEF_POSITION]))));
		evs.add(new EffortValue(EffortValue.SPA_NAME, Integer.valueOf(BattleFactoryUtils.nullToString(fields[SPA_POSITION]))));
		evs.add(new EffortValue(EffortValue.SPD_NAME, Integer.valueOf(BattleFactoryUtils.nullToString(fields[SPD_POSITION]))));
		evs.add(new EffortValue(EffortValue.SPE_NAME, Integer.valueOf(BattleFactoryUtils.nullToString(fields[SPE_POSITION]))));
		capSet.setEvs(evs);
		capSet.setAuthor(SET_AUTHOR);
		capSet.setSetNum(setNumber);
		return capSet;
	}
	
	public static List<CaptureSet> buildAll(List<String> lines) {
		List<CaptureSet> captureSets = new ArrayList<>();
		String previousName = "";
		int i = 1;
		Iterator<String> itr = lines.iterator();
		while(itr.hasNext()) {
			String set = itr.next();
			if(set != null) {
				String name = set.split(FILE_SEPARATOR)[0];
				//Reset set number when the capture changes
				if(!name.equalsIgnoreCase(previousName)) i = 1;
				captureSets.add(buildCaptureSet(set,i));
				previousName = name;
				i++;	
			}
		}
		return captureSets;
	}
}
