package vectors;

import java.util.Arrays;

public abstract class AbstractVector<T extends AbstractVector<T>> {
    protected final float[] components;
    public static final float epsilon = 1e-7f;
    private float x;
    private float y;
    private float z;
    private float w;

    public AbstractVector(float... components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    public abstract T createInstance(float... components);

    public int getDimension() {
        return components.length;
    }

    public float get(int index) {
        return components[index];
    }

    public float getX() {
        return components[0];
    }

    public float getY() {
        return components[1];
    }

    public float getZ() {
        return components[2];
    }

    public float getW() {
        return components[3];
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float[] getComponents(){
        return components.clone();
    }

    public void set(int index, float value) {
        components[index] = value;
    }

    public boolean isEqual(T other) {
        if (getDimension() != other.getDimension()) {
            return false;
        }
        for (int i = 0; i < getDimension(); i++) {
            if (Math.abs(get(i) - other.get(i)) >= epsilon) {
                return false;
            }
        }
        return true;
    }

    public T add(T other) throws IllegalArgumentException {
        if (getDimension() != other.getDimension()) {
            throw new IllegalArgumentException("Вектора должны быть одной размерности");
        }
        float[] result = new float[getDimension()];
        for (int i = 0; i < getDimension(); i++) {
            result[i] = get(i) + other.get(i);
        }
        return createInstance(result);
    }

    public void addToMe(T other) throws IllegalArgumentException {
        if (getDimension() != other.getDimension()) {
            throw new IllegalArgumentException("Вектора должны быть одной размерности");
        }
        for (int i = 0; i < getDimension(); i++) {
            components[i] += other.get(i);
        }
    }

    public T subtract(T other) throws IllegalArgumentException {
        if (getDimension() != other.getDimension()) {
            throw new IllegalArgumentException("Вектора должны быть одной размерности");
        }
        float[] result = new float[getDimension()];
        for (int i = 0; i < getDimension(); i++) {
            result[i] = get(i) - other.get(i);
        }
        return createInstance(result);
    }

    public void subtractFromMe(T other) throws IllegalArgumentException {
        if (getDimension() != other.getDimension()) {
            throw new IllegalArgumentException("Вектора должны быть одной размерности");
        }
        for (int i = 0; i < getDimension(); i++) {
            components[i] -= other.get(i);
        }
    }

    public void multiplyByScalar(float scalar) {
        for (int i = 0; i < getDimension(); i++) {
            components[i] *= scalar;
        }
    }

    public void divideByScalar(float scalar) {
        if (Math.abs(scalar) < epsilon) {
            throw new ArithmeticException("На ноль делить нельзя");
        }
        multiplyByScalar(1/scalar);
    }

    public float getLength() {
        float sum = 0;
        for (float component : components) {
            sum += component * component;
        }
        return (float) Math.sqrt(sum);
    }

    public void normalize() {
        float length = getLength();
        if (Math.abs(length) < epsilon) {
            throw new ArithmeticException("Нулевой вектор");
        }
        multiplyByScalar(1/length);
    }

    public T getNormalized() {
        float length = getLength();
        if (Math.abs(length) < epsilon) {
            throw new ArithmeticException("Нулевой вектор");
        }
        float[] normalizedComponents = new float[getDimension()];
        for (int i = 0; i < getDimension(); i++) {
            normalizedComponents[i] = components[i] / length;
        }
        return createInstance(normalizedComponents);
    }

    public float dot(T other) {
        if (getDimension() != other.getDimension()) {
            throw new IllegalArgumentException("Вектора должны быть одной размерности");
        }
        float result = 0;
        for (int i = 0; i < getDimension(); i++) {
            result += components[i] * other.get(i);
        }
        return result;
    }
    

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + Arrays.toString(components);
    }

}
