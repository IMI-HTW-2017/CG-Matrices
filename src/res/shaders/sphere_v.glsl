#version 400

layout(location = 0) in vec3 vertex;
layout(location = 1) in vec3 normal;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

out vec3 normalVector;
out vec4 pixelPosition;
out vec3 vertexColor;

void main() {
    /***************************/
    /* Einfarbige Seiten Tetra */
    /***************************/
    if (gl_VertexID < 3) {
        vertexColor = vec3(1.0, 0.0, 0.0);
    } else if (gl_VertexID < 6) {
        vertexColor = vec3(0.0, 1.0, 0.0);
    } else if (gl_VertexID < 9) {
        vertexColor = vec3(0.0, 0.0, 1.0);
    } else if (gl_VertexID < 12) {
        vertexColor = vec3(1.0, 0.0, 1.0);
    } else {
        vertexColor = vec3(0.0, 1.0, 1.0);
    }

    mat4 transformationMatrix = viewMatrix * modelMatrix;

    mat3 normalMatrix = inverse(transpose(mat3(transformationMatrix)));
    normalVector = normalMatrix * normal;

    pixelPosition = transformationMatrix * vec4(vertex, 1.0);
    gl_Position = projectionMatrix * pixelPosition;
}