package ab3.math;

public class Utilities {

    public static float[] calculateObjectNormals(float[] vertices) {
        float[] normals = new float[vertices.length];
        for (int i = 0; i < vertices.length; i += 9) {
            Vector3 vector1 = new Vector3(vertices[i + 3] - vertices[i], vertices[i + 4] - vertices[i + 1], vertices[i + 5] - vertices[i + 2]);
            Vector3 vector2 = new Vector3(vertices[i + 6] - vertices[i], vertices[i + 7] - vertices[i + 1], vertices[i + 8] - vertices[i + 2]);
            Vector3 normal = vector1.cross(vector2).normalize();

            for (int j = 0; j < 3; j++) {
                normals[i + j * 3] = normal.x();
                normals[i + j * 3 + 1] = normal.y();
                normals[i + j * 3 + 2] = normal.z();
            }
        }

        return normals;
    }
}
