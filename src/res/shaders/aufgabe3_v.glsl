#version 400

layout(location = 0) in vec3 vertex;
layout(location = 1) in vec3 normal;
layout(location = 2) in vec2 uv;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

out vec3 normalVector;
out vec2 uvCoord;
out vec4 pixelPosition;

void main() {

    mat4 transformationMatrix = viewMatrix * modelMatrix;

    mat3 normalMatrix = inverse(transpose(mat3(transformationMatrix)));
    normalVector = normalMatrix * normal;

    uvCoord = uv;

    pixelPosition = transformationMatrix * vec4(vertex, 1.0);
    gl_Position = projectionMatrix * pixelPosition;
}