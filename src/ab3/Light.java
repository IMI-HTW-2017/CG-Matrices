package ab3;

import ab3.math.Vector3;

class Light {

    private Vector3 position;
    private Vector3 color;
    private float intensity;

    Vector3 getPosition() {
        return position;
    }

    Vector3 getColor() {
        return color;
    }

    float getIntensity() {
        return intensity;
    }

    Light(Vector3 position, Vector3 color, float intensity) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

}
