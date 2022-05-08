package shi.alg.ch23.five;

import java.util.HashMap;
import java.util.Map;

public class TranspositionTable {
	static Map<Long, TT_ENTRY> tt_map = new HashMap<Long,TT_ENTRY>();

	public static void InitTranspositionTable()
	{
	    tt_map.clear();
	}

	public static void ResetTranspositionTable()
	{
		tt_map.clear();
	}

	public static boolean  LookupTranspositionTable(long hash, TT_ENTRY ttEntry)
	{
		if(tt_map.containsKey(hash)){
			ttEntry=tt_map.get(hash);
			 return true;
		}
	    return false;
	}

	public static void StoreTranspositionTable(long hash, TT_ENTRY ttEntry)
	{
		TT_ENTRY old=tt_map.get(hash);
	    if(null != old)
	    {
	        if(ttEntry.depth >= old.depth)
	        {
	            old = ttEntry;
	            tt_map.put(hash, old);
	        }
	    }
	    else
	    {
	        tt_map.put(hash, ttEntry);
	    }
	}
}
