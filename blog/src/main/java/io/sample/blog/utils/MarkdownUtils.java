package io.sample.blog.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkdownUtils {
    
    // Markdown format to HTML format used with commonmark API
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
    
    // Create id for html and new table
    public static String markdownToHtmlExtensions(String markdown) {
        // Create id for <h1, h2, h3...>
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        
        // Table change to HTML format
        List<Extension> tableExtension = Collections.singletonList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tableExtension).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer =
        HtmlRenderer.builder().extensions(headingAnchorExtensions).extensions(tableExtension).attributeProviderFactory(
        attributeProviderContext -> new CustomAttributeProvider()).build();
        return renderer.render(document);
    }
    
    // Handle the tags attributes
    private static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            // Change the tags attributes to _blank
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }
    }
    
    // Test
    public static void main(String[] args) {
        String table = "| hello | hi   | 哈哈哈   |\n" +
                       "| ----- | ---- | ----- |\n" +
                       "| 斯维尔多  | 士大夫  | f啊    |\n" +
                       "| 阿什顿发  | 非固定杆 | 撒阿什顿发 |\n" +
                       "\n";
        String test = "[imCoding 爱编程](https://kickccat.github.io/)";
        System.out.println(markdownToHtmlExtensions(test));
        System.out.println(markdownToHtmlExtensions(table));
        System.out.println(markdownToHtml(table));
    }
}
