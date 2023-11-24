package hk.edu.polyu.comp.comp3211.group6.Controller;

public class Token {
    public final TokenType type;
    public final String lexeme;
    public final Object literal;

    public Token(TokenType type, String lexeme, Object literal){
        this.type = type;
        this. lexeme = lexeme;
        this. literal = literal;
    }

    public String toString(){
        if (this.literal == null)
            return type + " " + lexeme;
        return type + " " + lexeme + " " + literal;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        else if (!(obj instanceof Token token)) return false;
        else if (this.literal == null && token.literal == null)
            return this.type.equals(token.type) && this.lexeme.equals(token.lexeme);
        else if (this.literal == null || token.literal == null)
            return false;
        else return this.type.equals(token.type) && this.lexeme.equals(token.lexeme)
                && this.literal.equals(token.literal);
    }
}
