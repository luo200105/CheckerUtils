package app.cnode.utils;

public class StringTools {

    /**
     * Checks if the provided object is considered null. An object is considered null if it meets
     * any of the following criteria:
     * 1. It is {@code null}.
     * 2. It is a String and equals to "null".
     * 3. It is a String and is empty ("").
     *
     * @param obj The object to check.
     * @return {@code true} if the object is considered null based on the criteria above, {@code false} otherwise.
     */
    public static boolean isNull(Object obj) {
        // Check for strict null
        if (obj == null) {
            return true;
        }

        // Check for the String "null" and empty string, ensuring obj is indeed a String
        if (obj instanceof String) {
            String str = (String) obj;
            return "null".equals(str) || str.isEmpty();
        }

        // If none of the above, the object is not considered null
        return false;
    }

}
