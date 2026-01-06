package com.theralink.common.dataStructure;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
public class TimedHashMap<T, V> implements Map<T, V> {

	private final Map<T, TimedValueWrapper> delegate = new ConcurrentHashMap<T, TimedValueWrapper>();
	private final long                      EXPIRED_TIME_IN_MILLISEC;
	private final Timer                     timer;

	public TimedHashMap() {
		this(5000L);
	}

	public TimedHashMap(long expiry) {
		this(expiry, 1000);
	}

	public TimedHashMap(long expiry, long interval) {
		EXPIRED_TIME_IN_MILLISEC = expiry;
		timer = new Timer();
		timer.schedule(new ClearerReminder(), 0, interval);
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return delegate.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return delegate.containsValue(value);
	}

	@Override
	public V get(Object key) {
		TimedValueWrapper w = delegate.get(key);
		if (w == null) {
			return null;
		}
		return w._value;
	}

	@Override
	public V put(T key, V value) {
		boolean found = containsKey(key);
		if (found) {
			return null;
		}
		TimedValueWrapper w = delegate.put(key, new TimedValueWrapper(value));
		if (w == null) {
			return null;
		}
		return w._value;
	}

	@Override
	public V remove(Object key) {
		TimedValueWrapper w = delegate.remove(key);
		if (w == null) {
			return null;
		}
		return w._value;
	}

	@Override
	public void putAll(Map<? extends T, ? extends V> m) {
		m.forEach((t, v) -> this.put(t, v));
	}

	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public Set<T> keySet() {
		return delegate.keySet();
	}

	@Override
	public Collection<V> values() {
		return delegate.values().stream().map((w) -> w._value).collect(Collectors.toSet());
	}

	@Override
	public Set<Entry<T, V>> entrySet() {
		return delegate.entrySet().stream().map((e) -> new MutableEntry(e.getKey(), e.getValue()._value)).collect(Collectors.toSet());
	}

	private class ClearerReminder extends TimerTask {

		@Override
		public void run() {
			Set<Entry<T, TimedValueWrapper>> entrySet = delegate.entrySet().stream().filter((e) -> (System.currentTimeMillis() - e.getValue()._time) > EXPIRED_TIME_IN_MILLISEC).collect(Collectors.toSet());

			entrySet.stream().forEach((entry) -> remove(entry.getKey()));
		}

	}

	private class MutableEntry implements Entry<T, V> {
		private V value;
		private T key;

		public MutableEntry(T key, V value) {
			super();
			this.value = value;
			this.key = key;
		}

		@Override
		public T getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

	}

	private class TimedValueWrapper {
		final V    _value;
		final long _time;

		TimedValueWrapper(V value) {
			_value = value;
			_time = System.currentTimeMillis();
		}
	}
}
