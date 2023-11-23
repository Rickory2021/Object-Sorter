package objectsorter.textanalyzer.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlTag {
	private String tagName;
	private boolean hasSlash, hasEndCarrot, isSingleton;
	public final static ArrayList<String> singletonTagList = new ArrayList<String>(Arrays.asList(new String[]{
			"!doctype", "area", "base", "basefont", "bgsound", "br", "col", "embed", "frame", "hr", "img", "input", "isindex", "keygen", "link", 
			"meta", "param", "spacer", "source", "track","wbr"}));
	// Excludes rp, rt, ruby(too weird)
	public final static ArrayList<String> pairedTagList = new ArrayList<String>(Arrays.asList(new String[]{
			"a", "abbr", "acronym", "address", "applet", "article", "aside", "audio", "b", "bdi", "bdo", "big", "blockquote", "body", "button", 
			"canvas", "caption", "center", "cite", "code", "colgroup", "data", "datalist", "dd", "del", "details", "dfn", "dialog", "dir", "div", 
			"dl", "dt", "em", "fieldset", "figcaption", "figure", "font", "footer", "form", "frameset", "h1", "h2", "h3", "h4", "h5", "h6", "head",
			"header", "hgroup", "html", "i", "iframe", "ins", "kbd", "label", "legend", "li", "main", "map", "mark", "marquee", "menu", "menuitem",
			"meter", "nav", "nobr", "noembed", "noframes", "noscript", "object", "ol", "optgroup", "option", "output", "p", "picture", "pre", 
			"progress", "q", "s", "samp", "script", "section", "select", "small", "span", "strike", "strong", "style", "sub", "summary", "sup", "svg",
			"table", "tbody", "td", "template", "textarea", "tfoot", "th", "thread", "time", "title", "tr", "tt", "u", "ul", "var", "video", "xmp"}));
	public final static ArrayList<String> reservedHypenedString= new ArrayList<String>(Arrays.asList(new String[]{
			"annotation-xml", "color-profile", "font-face", "font-face-src", "font-face-uri", "font-face-format", "font-face-name", "missing-glyph"}));
	public final static String commentTag = "!--";
	// https://html.spec.whatwg.org/#valid-custom-element-name
	public final static String regexPCENChar = "[\\.0-9_a-z\\xB7\\xC0-\\xD6\\xD8-\\xF6\\xF8-\\u037D\\u037F-\\u1FFF\\u200C\\u200D\\u203F\\u2040\\u2070-\\u218F\\u2C00-\\u2FEF\\u3001-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFFD]|[\\x{00010000}-\\x{000EFFFF}]";
	public final static String regexCustomTag = String.format("^[a-z](?:%s)*-(?:%s)*$",regexPCENChar,regexPCENChar);
	
	static Pattern pattern = Pattern.compile(regexCustomTag,Pattern.UNICODE_CHARACTER_CLASS);
	
	public HtmlTag() {
		this.tagName=null;
		this.hasSlash=false;
	}
	
	public HtmlTag(String tagText) {
		this.hasSlash=false;
		this.hasEndCarrot=false;
		if(tagText.contains("!--")) {
			this.tagName="comment";
			return;
		}
		
		tagText.trim();
		int startingIndex = 0, endingIndex=tagText.length();
		startingIndex=tagText.indexOf('<')+1;
		if(tagText.contains(">")) {
			this.hasEndCarrot=true;
			endingIndex=tagText.length()-1;
		}else {
			endingIndex=tagText.length();
		}
		if(startingIndex<=0)startingIndex=0;
		/*if(tagText.length()>0 && tagText.charAt(0)=='<')startingIndex=1;
		if(tagText.length()>1 && tagText.charAt(tagText.length()-1)=='>') {
			this.hasEndCarrot=true;
			if(tagText.length()>2 && tagText.charAt(tagText.length()-2)=='/') {
				endingIndex=tagText.length()-2;
				this.hasSlash=true;
			}else {
				endingIndex=tagText.length()-1;
			}
		}*/
		tagText=tagText.substring(startingIndex,endingIndex);
		this.tagName=tagText.toLowerCase();
		this.hasSlash=hasSlash(tagText);
		this.isSingleton=isSingletonTag(tagName.trim());
		//if(isSingletonTag(this.tagName))this.hasSlash=false;
	}

	public String getTagName() {
		return tagName;
	}

	/*public void setTagName(String tagName) {
		this.tagName = tagName;
	}*/
	
	/*public String getEndingTagString() {
		if(isSingleton) {
			return ">";
		}else if(isComment) {
			return "-->";
		}else {
			return String.format("</%s>",tagName);
		}
	}*/
	
	public boolean hasSlash() {
		return hasSlash;
	}
	
	public boolean hasEndCarrot() {
		return hasEndCarrot;
	}

	public static String clearArrowTagString(String tagText) {
		int startingIndex=tagText.indexOf('<');
		if(startingIndex!=-1)tagText= tagText.substring(startingIndex+1);
		int endingIndex=tagText.lastIndexOf('>');
		if(endingIndex!=-1)tagText= tagText.substring(0,endingIndex);
		//System.out.println("arrowLess|"+tagText+"|");
		if(tagText.length()>1 && tagText.charAt(tagText.length()-1)=='/')
			tagText= tagText.substring(0,tagText.length()-1);
		return tagText.trim();
	}
	
	public static boolean isValidCustomTag(String tagText) {
		tagText=clearArrowTagString(tagText);
		if(reservedHypenedString.contains(tagText))return false;
		Matcher matcher = pattern.matcher(tagText);
		return matcher.matches();
		//return tagText.matches(pattern);
	}
	
	public static boolean isSingletonTag(String tagText) {
		tagText=clearArrowTagString(tagText);
		return singletonTagList.contains(tagText);
		//return tagText.matches(pattern);
	}
	
	
	public  boolean isSingleton() {
		return isSingleton;
		//return tagText.matches(pattern);
	}
	
	public static boolean isPairedTag(String tagText) {
		tagText=clearArrowTagString(tagText);
		return pairedTagList.contains(tagText);
		//return tagText.matches(pattern);
	}
	
	public static boolean hasSlash(String tagText) {
		if(tagText.indexOf('/')!=0)return true;
		return false;
	}
	
	public boolean hasCDATA() {
		if(tagName.equals(commentTag)||tagName.equals("script")) {
			return true;
		}
		return false;
	}
	
	public boolean isCommentTag() {
		return tagName.equals("comment");
	}
	public boolean isScriptTag() {
		return tagName.equals("script");
	}
}
