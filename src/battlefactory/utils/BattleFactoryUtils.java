package battlefactory.utils;

public class BattleFactoryUtils {
	
	//If the String is null, make it an empty String to avoid random NullPointerExceptions
	public static String nullToString(String str) {
		if(str == null) str = "";
		return str;
	}
	
	//toUpperCase + replace all separators with blank since the capture names cannot have separators
	public static String toUpperCaseNoSeparator(String str) {
		str = nullToString(str);
		str = str.toUpperCase();
		str = str.replaceAll("[-_\\.\\s]", "");
		return str;
	}
	
	//toUpperCase + replace all separators with underscore (_) since the move and item names need to be separated by underscores
	public static String toUpperCaseWithSeparator(String str) {
		str = nullToString(str);
		str = str.toUpperCase();
		str = str.replaceAll("[-_\\.\\s]", "_");
		return str;
	}
}
