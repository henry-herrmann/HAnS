/*
Copyright 2021 Herman Jansson & Johan Martinson

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package se.ch.HAnS.syntaxHighlighting.fileAnnotations;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import se.ch.HAnS.fileAnnotation.FileAnnotationLexerAdapter;
import se.ch.HAnS.fileAnnotation.psi.FileAnnotationTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FileAnnotationSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey FILENAME =
            createTextAttributesKey("FILENAME", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey FEATURE_FILE_SEPARATOR =
            createTextAttributesKey("SEPARATOR", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey FEATURE_FILE_FOLDER_CS =
            createTextAttributesKey("CS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("HAnS_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] FEATURE_FILE_SEPARATOR_KEYS = new TextAttributesKey[]{FEATURE_FILE_SEPARATOR};
    private static final TextAttributesKey[] FEATURE_FILE_CS_KEYS = new TextAttributesKey[]{FEATURE_FILE_FOLDER_CS};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new FileAnnotationLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(FileAnnotationTypes.SEPARATOR)) {
            return FEATURE_FILE_SEPARATOR_KEYS;
        }  else if (tokenType.equals(FileAnnotationTypes.CS) ||
                tokenType.equals(FileAnnotationTypes.DOT) ||
                tokenType.equals(FileAnnotationTypes.CM)) {
            return FEATURE_FILE_CS_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
