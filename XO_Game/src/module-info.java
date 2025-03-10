module XO_Game {
    requires java.rmi;  // Nécessaire pour utiliser RMI
    exports rmi.xo;    // Permet à java.rmi d'accéder à vos classes dans rmi.xo
}