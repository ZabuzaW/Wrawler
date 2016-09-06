package de.zabuza.webcrawler.database;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utility class that links maps and their ids.
 * @author Zabuza
 *
 */
public final class MapTableDb implements Iterable<Entry<Integer, String>> {
	/**
	 * Singleton instance of this class.
	 */
	private static MapTableDb instance = null;
	/**
	 * Separator character for values in the text representation.
	 */
	private static final String VALUE_SEPARATOR = ",";
	/**
	 * Enclosing character for values in the text representation.
	 */
	private static final String ENCLOSER = "\"";
	/**
	 * Separator character for entries in the text representation.
	 */
	public static final String ENTRY_SEPARATOR = ";";
	
	/**
	 * Dictionary for id to map access.
	 */
	private final Map<Integer, String> idToMap = new HashMap<Integer, String>();
	/**
	 * Dictionary for map to id access.
	 */
	private final Map<String, Integer> mapToId = new HashMap<String, Integer>();
	/**
	 * Dictionary for map to active access.
	 */
	private final Map<String, Integer> mapToActive = new HashMap<String, Integer>();
	
	/**
	 * Creates a new mapTableDb object.
	 */
	private MapTableDb() {
		//Generated by MapTableParseTool : mapTable.csv
		idToMap.put(1, "Altis");
		mapToActive.put("Altis", 1);
		idToMap.put(2, "Aliabad");
		mapToActive.put("Aliabad", 1);
		idToMap.put(3, "Bukovina");
		mapToActive.put("Bukovina", 1);
		idToMap.put(4, "Bystrica");
		mapToActive.put("Bystrica", 1);
		idToMap.put(5, "Caribou");
		mapToActive.put("Caribou", 1);
		idToMap.put(6, "Chernarus");
		mapToActive.put("Chernarus", 1);
		idToMap.put(7, "Clafghan");
		mapToActive.put("Clafghan", 1);
		idToMap.put(8, "Imrali Island");
		mapToActive.put("Imrali Island", 1);
		idToMap.put(9, "Namalsk");
		mapToActive.put("Namalsk", 1);
		idToMap.put(10, "Southern Sahrani");
		mapToActive.put("Southern Sahrani", 1);
		idToMap.put(11, "Summer Sangin");
		mapToActive.put("Summer Sangin", 1);
		idToMap.put(12, "Virtual Reality");
		mapToActive.put("Virtual Reality", 1);
		idToMap.put(13, "Utes");
		mapToActive.put("Utes", 1);
		idToMap.put(14, "Wake Island");
		mapToActive.put("Wake Island", 1);
		idToMap.put(15, "Sahrani");
		mapToActive.put("Sahrani", 1);
		idToMap.put(16, "United Sahrani");
		mapToActive.put("United Sahrani", 1);
		idToMap.put(17, "Fallujah");
		mapToActive.put("Fallujah", 1);
		idToMap.put(18, "Abottabad");
		mapToActive.put("Abottabad", 1);
		idToMap.put(19, "Chernarus Summer");
		mapToActive.put("Chernarus Summer", 1);
		idToMap.put(20, "Porto");
		mapToActive.put("Porto", 1);
		idToMap.put(21, "Rahmadi");
		mapToActive.put("Rahmadi", 1);
		idToMap.put(22, "Takistan Mountains");
		mapToActive.put("Takistan Mountains", 1);
		idToMap.put(23, "Takistan");
		mapToActive.put("Takistan", 1);
		idToMap.put(24, "Zargabad");
		mapToActive.put("Zargabad", 1);
		idToMap.put(25, "Desert");
		mapToActive.put("Desert", 1);
		idToMap.put(26, "Proving Grounds");
		mapToActive.put("Proving Grounds", 1);
		idToMap.put(27, "Shapur");
		mapToActive.put("Shapur", 1);
		idToMap.put(28, "Stratis");
		mapToActive.put("Stratis", 1);
		idToMap.put(29, "Island Panthera");
		mapToActive.put("Island Panthera", 1);
		idToMap.put(30, "Esbekistan");
		mapToActive.put("Esbekistan", 0);
		idToMap.put(31, "Emita");
		mapToActive.put("Emita", 0);
		idToMap.put(32, "Lingor");
		mapToActive.put("Lingor", 0);
		idToMap.put(33, "FDF Podagorsk");
		mapToActive.put("FDF Podagorsk", 1);
		idToMap.put(34, "Celle");
		mapToActive.put("Celle", 0);
		idToMap.put(35, "Reshmaan Province");
		mapToActive.put("Reshmaan Province", 0);
		idToMap.put(36, "Isla Duala");
		mapToActive.put("Isla Duala", 0);
		idToMap.put(37, "Cicada");
		mapToActive.put("Cicada", 0);
		idToMap.put(38, "Quesh-Kibrul");
		mapToActive.put("Quesh-Kibrul", 0);
		idToMap.put(39, "Isola di Capraia");
		mapToActive.put("Isola di Capraia", 1);
		idToMap.put(40, "Celle2");
		mapToActive.put("Celle2", 0);
		idToMap.put(41, "Hazar-Kot Valley");
		mapToActive.put("Hazar-Kot Valley", 0);
		idToMap.put(42, "Unknown");
		mapToActive.put("Unknown", 0);
		
		for (Entry<Integer, String> entry : idToMap.entrySet()) {
			mapToId.put(entry.getValue(), entry.getKey());
		}
	}
	/**
	 * Returns the map that is represented by the given id.
	 * @param id Id of the map
	 * @return Map that is represented by the given id
	 */
	public String getMap(int id) {
		String map = idToMap.get(id);
		if (map == null) {
			System.err.println("Database table does not know map with id: " + id);
		}
		return map;
	}
	/**
	 * Returns the id of the given map.
	 * @param map Map to get id of
	 * @return Id of the map
	 */
	public Integer getId(String map) {
		Integer id = mapToId.get(map);
		if (id == null) {
			System.err.println("Database table does not know id of map: " + map);
		}
		return id;
	}

	@Override
	public Iterator<Entry<Integer, String>> iterator() {
		return idToMap.entrySet().iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Entry<Integer, String> entry : idToMap.entrySet()) {
			builder.append(ENCLOSER + entry.getKey() + ENCLOSER + VALUE_SEPARATOR);
			builder.append(ENCLOSER + entry.getValue() + ENCLOSER + VALUE_SEPARATOR);
			builder.append(ENCLOSER + mapToActive.get(entry.getValue()) + ENCLOSER + ENTRY_SEPARATOR);
		}
		builder.delete(builder.length() - ENTRY_SEPARATOR.length(), builder.length());
		return builder.toString();
	}
	
	/**
	 * Gets the singleton instance of this class.
	 * @return Singleton instance of this class
	 */
	public static MapTableDb getInstance() {
		if (instance == null) {
			instance = new MapTableDb();
		}
		return instance;
	}

}