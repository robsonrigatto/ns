package br.com.rr.vogalstream.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.rr.vogalstream.Stream;

public class VogalStream implements Stream {
	
	private static final List<Character> VOWELS;
	
	static {
		VOWELS = new ArrayList<Character>();
		VOWELS.add('a'); VOWELS.add('A'); 
		VOWELS.add('e'); VOWELS.add('E');
		VOWELS.add('i'); VOWELS.add('I');
		VOWELS.add('o'); VOWELS.add('O');
		VOWELS.add('u'); VOWELS.add('U');
	}
	
	private String word;
	private Integer currentIndex;
	private Character lastCharacter;
	
	public VogalStream(String word) {
		this.word = word;
		this.currentIndex = 0;
	}

	public char getNext() {
		return this.lastCharacter;
	}

	public boolean hasNext() {
		if(currentIndex >= (word.length()-2)) {
			return false;
		}
		lastCharacter = this._getNext();
		return lastCharacter != (char) -1;
	}
	
	private char _getNext() {
		String substring = this.word.substring(this.currentIndex);
		char[] substringArray = substring.toCharArray();
		
		for(int i = 0; i < substringArray.length - 2; i++) {
			char first = substringArray[i];
			char second = substringArray[i+1];
			char third = substringArray[i+2];
			
			if(VOWELS.contains(first) && !VOWELS.contains(second) && VOWELS.contains(third)) {
				Boolean repeated = false;
				
				for(int j = i + 3; j < substringArray.length; j++) {
					if(substringArray[j] == third) {
						repeated = true;
						break;
					}
				}
				
				if(!repeated) {
					this.currentIndex += i+1;
					return third;
				}
			}
		}
		
		return (char) -1;
	}
}
