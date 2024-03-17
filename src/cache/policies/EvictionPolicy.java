package cache.policies;

public interface EvictionPolicy<Key> {

	/* Get key to be evicted acc to policy */
	Key evict();

	/* Update DS acc to key Accessed */
	void keyAccessed(Key key);

}
