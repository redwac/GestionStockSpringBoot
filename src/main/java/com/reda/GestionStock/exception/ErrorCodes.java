package com.reda.GestionStock.exception;

public enum  ErrorCodes {

    ARTICLE_NOT_FOUND(1000),ARTICLE_NOT_VALID(1001),
    // TODO complet the rest of the errors codes
    CATEGORY_NOT_FOUND(2000),
    CLIENT_NOT_FOUND(3000),
    COMMANDE_CLIENT_NOT_FOUND(4000), COMMANDE_CLIENT_NOT_VALID(4001),
    COMMANDE_FOURNISSEUR_FOUND(5000),
    ENTREPRISE_NOT_FOUND(6000),
    FOURNISSEUR_NOT_FOUND(7000),
    LIGNE_COMMANDE_CLIENT_NOT_FOUND(8000),
    LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND(9000),
    LIGNE_VENTE_NOT_FOUND(10000),
    MVSTK_NOT_FOUND(11000),
    UTILISATEUR_NOT_FOUND(12000),
    VENTES_NOT_FOUND(13000),
    BAD_CREDENTIALS(1400);


    private int code ;

    ErrorCodes(int code) {
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}
