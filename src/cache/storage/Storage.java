package cache.storage;

public interface Storage<Key, Value> {

	void put(Key key, Value value);

	Value get(Key key);

	void remove(Key key);

}
