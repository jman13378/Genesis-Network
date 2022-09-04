package me.jonathan;
public class Triplet<T, U, V> {
	/**
	 * 
	 */
    private final T key;
    private final U value;
    private final V subvalue;
    /**
     * 
     * @param T The key also known as the identifier
     * @param U The value also known as the value assigned to the key
     * @param V The subvalue also known as the not so important value
     */
    public Triplet(T key, U value, V subvalue) {
        this.key = key;
        this.value = value;
        this.subvalue = subvalue;
    }
    /** 
     * this is the first value
     * @return T value 1
     */
    public T getKey() { return key; }
    /** 
     * this is the first value
     * @return U value 2
     */
    public U getValue() { return value; }
    /** 
     * this is the first value
     * @return V value 3
     */
    public V getSubValue() { return subvalue; }
}