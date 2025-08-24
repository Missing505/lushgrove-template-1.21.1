#version 150

uniform sampler2D DiffuseSampler0;

in vec2 texCoord;

out vec4 fragColor;
uniform float STime;
uniform float Seed;

float random (in vec2 st) {
    return fract(sin(dot(st.xy,
    vec2(12.9898,78.233)))
    * 43758.5453123);
}

float noise (in vec2 st) {
    vec2 i = floor(st);
    vec2 f = fract(st);

    // Four corners in 2D of a tile
    float a = random(i);
    float b = random(i + vec2(1.0, 0.0));
    float c = random(i + vec2(0.0, 1.0));
    float d = random(i + vec2(1.0, 1.0));

    // Smooth Interpolation

    // Cubic Hermine Curve.  Same as SmoothStep()
    vec2 u = f*f*(3.0-2.0*f);
    // u = smoothstep(0.,1.,f);

    // Mix 4 coorners percentages
    return mix(a, b, u.x) +
    (c - a)* u.y * (1.0 - u.x) +
    (d - b) * u.x * u.y;
}

void main() {
    vec3 newColor = vec3(noise(texCoord*3.0),noise(texCoord*1.24),noise(texCoord*8.11));
    fragColor = texture(DiffuseSampler0,texCoord) + (vec4(newColor * (noise((texCoord+Seed+(STime/2.0))*4.0)), 1.0)* STime/5.0);
}
