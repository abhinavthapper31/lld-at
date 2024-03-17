package cache;

import cache.exceptions.StorageFullException;
import cache.policies.EvictionPolicy;
import cache.storage.Storage;

public class Cache<Key, Value> {

	/* a cache has 2 properties a storage and a policy to evict */
	private Storage<Key, Value> storage;
	private EvictionPolicy<Key> evictionPolicy;

	public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
		this.evictionPolicy = evictionPolicy;
		this.storage = storage;
	}

	public void put(Key key, Value value) {

		try {
			storage.put(key, value);

			evictionPolicy.keyAccessed(key);
		} catch (StorageFullException ex) {
			System.out.print("Exception occured while storing -> " + ex.getLocalizedMessage());
			System.out.print("Going to evict key");

			Key keyToBeEvicted = evictionPolicy.evict();

			this.storage.remove(keyToBeEvicted);
			System.out.println("Going to  evict item..." + keyToBeEvicted);
			put(key, value);

		}

	}

	public Value get(Key key) {
		Value val = null;
		try {
			val = storage.get(key);
			evictionPolicy.keyAccessed(key);
		} catch (Exception ex) {
			System.out.print("Exception occured while storing -> " + ex.getLocalizedMessage());
		}
		return val;
	}

}
