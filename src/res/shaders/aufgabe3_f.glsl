#version 400

in vec3 vertexColor;
in vec3 normalVector;
in vec2 uvCoord;
in vec4 pixelPosition;

uniform vec3 lightPosition;
uniform vec3 lightColor;
uniform float lightIntensity;

uniform sampler2D textureSampler;

out vec3 pixelColor;

float ambientIntensity = 0.3;

//Object attributes
float ambientFactor = 0.5;
float diffuseFactor = 0.2;
float specularFactor = 0.8;
float specularDamper = 500;

void main() {
    float factorSum = ambientFactor + diffuseFactor + specularFactor;
    float kAmbient = ambientFactor / factorSum;
    float kDiffuse = diffuseFactor / factorSum;
    float kSpecular = specularFactor / factorSum;

    vec3 unitToLightVector = normalize(lightPosition - pixelPosition.xyz);
    vec3 unitToCameraVector = normalize(-pixelPosition.xyz);
    vec3 unitReflectionVector = reflect(-unitToLightVector, normalVector);

    float lightAmbient = ambientIntensity * kAmbient;
    float lightDiffuse = lightIntensity * max(0.0, dot(unitToLightVector, normalVector)) * kDiffuse;
    float lightSpecular = lightIntensity * pow(max(0.0, dot(unitReflectionVector, unitToCameraVector)), 5) * kSpecular;

    vec3 light = (lightAmbient + lightDiffuse + lightSpecular) * lightColor;

    vec3 textureColor = vec3(texture(textureSampler, uvCoord));

    pixelColor = (textureColor + light) / 2;
}