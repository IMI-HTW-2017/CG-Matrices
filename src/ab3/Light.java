package ab3;

import ab3.math.Vector3;

class Light {

    private Vector3 position;
    private Vector3 color;
    private float intensity;

    private float moveSpeed;

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

        moveSpeed = 0.1f;
    }

    void moveUp() {
        position = new Vector3(position.x(), position.y() + moveSpeed, position.z());
    }

    void moveDown() {
        position = new Vector3(position.x(), position.y() - moveSpeed, position.z());
    }

    void moveLeft() {
        position = new Vector3(position.x() - moveSpeed, position.y(), position.z());
    }

    void moveRight() {
        position = new Vector3(position.x() + moveSpeed, position.y(), position.z());
    }

}
