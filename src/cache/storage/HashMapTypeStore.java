package cache.storage;

import java.util.HashMap;
import java.util.Map;

import cache.exceptions.ElementNotFoundException;
import cache.exceptions.StorageEmptyException;
import cache.exceptions.StorageFullException;

public class HashMapTypeStore<Key, Value> implements Storage<Key, Value> {

	Map<Key, Value> storage;
	private final Integer capacity;

	public HashMapTypeStore(Integer capacity) {
		this.capacity = capacity;
		storage = new HashMap<>();
	}

	@Override
	public void put(Key key, Value value) {
		if (isStorageFull()) {
			System.out.print("Storage is full !");
			throw new StorageFullException("Capacity Full.....");
		}
		storage.put(key, value);
	}

	@Override
	public Value get(Key key) {

		if (storage.isEmpty()) {
			System.out.print("Storage is empty !");
			throw new StorageEmptyException("Capacity is 0.....");
		}

		if (storage.get(key) == null) {
			System.out.print("Element does not existing!");
			throw new ElementNotFoundException("Element does not exist.....");
		}

		return storage.get(key);
	}

	private boolean isStorageFull() {
		return storage.size() == capacity;
	}

	@Override
	public void remove(Key key) {
		if (!storage.containsKey(key)) {
			throw new ElementNotFoundException(key + "doesn't exist in cache. Can not remove");
		}
		storage.remove(key);
	}

}
