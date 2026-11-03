package shortlink;

/** Ce qu'on accepte comme URL longue, et ce qu'on en extrait. */
public final class Urls {

    public static final int MAX_LENGTH = 2000;
    public static final int MIN_LENGTH = 0;

    private Urls() {
    }

    /**
     * §2 🔒 Une URL valide :
     * <ul>
     *   <li>n'est pas null, et a entre 1 et {@link #MAX_LENGTH} caractères ;</li>
     *   <li>commence par {@code http://} ou {@code https://}, en majuscules ou en minuscules ;</li>
     *   <li>a au moins un caractère après {@code ://}, et ce caractère n'est ni {@code /}, ni {@code ?}, ni {@code #} ;</li>
     *   <li>ne contient aucun espace ni caractère de contrôle (retour à la ligne, tabulation…).</li>
     * </ul>
     */
    public static boolean isValid(String url) {

       if(url == null || url.length()<MIN_LENGTH || url.length()>MAX_LENGTH ) {
           return false;
       }
        String lower = url.toLowerCase();
        int longueur = 0;

        if (lower.startsWith("https://")) {
            longueur = 8;
        } else if (lower.startsWith("http://")) {
            longueur = 7;
        } else {
            return false;
        }

        if (url.length() <= longueur){
            return  false;
        }

        char premierCharSite = url.charAt(longueur);
        if (premierCharSite == '/' || premierCharSite == '?' || premierCharSite == '#') {
            return false; // Pas de nom de site valide (ex: http:///)
        }
        
        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            if (Character.isWhitespace(c) || Character.isISOControl(c)) {
                return false;
            }
        }

        // Tout est valide
        return true;

    }

    /**
     * §5 🔵 Le domaine d'une URL valide, en minuscules, sans {@code www.}, sans port, sans
     * identifiant : {@code https://www.FoodFast.fr:8443/menu} donne {@code foodfast.fr}.
     *
     * 🔒 Dans {@code https://foodfast.fr@evil.example/}, ce qui précède {@code @} est un identifiant :
     * le domaine est {@code evil.example}.
     *
     * @throws IllegalArgumentException si l'URL n'est pas valide
     */
    public static String domain(String url) {
        throw new UnsupportedOperationException("§5 : à écrire");
    }
}
