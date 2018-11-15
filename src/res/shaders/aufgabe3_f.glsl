#version 330

in vec3 vertexColor;

out vec3 pixelColor;

void main() {
    pixelColor = vertexColor;
}