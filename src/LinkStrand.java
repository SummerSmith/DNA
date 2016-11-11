
public class LinkStrand implements IDnaStrand{
	
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	
	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	private Node list;
	private int dex;
	private int count;
	
	public LinkStrand(String DNA) {
		initialize(DNA);
	}
	
	public LinkStrand() {
		this("");
	}
	
	@Override
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {

		int pos = 0;
		int start = 0;
		String search = myFirst.info;
		boolean first = true;
		IDnaStrand ret = null;
		if(myAppends > 0) {
			throw new UnsupportedOperationException(); 
		}

		// code identical to StringStrand, both String and StringBuilder
		// support .substring and .indexOf
		
		while ((pos = search.indexOf(enzyme, pos)) >= 0) {
			if (first) {
				ret = getInstance(search.substring(start, pos));
				first = false;
			} else {
				ret.append(search.substring(start, pos));

			}
			start = pos + enzyme.length();
			ret.append(splicee);
			pos++;
		}

		if (start < search.length()) {
			// NOTE: This is an important special case! If the enzyme
			// is never found, return an empty String.
			if (ret == null) {
				ret = getInstance("");
			} else {
				ret.append(search.substring(start));
			}
		}
		return ret;
	}
	
	@Override
	public long size() {	
		return mySize;
	}

	@Override
	public void initialize(String source) {
		
		Node toAdd = new Node(source);
		myFirst = toAdd;
		myLast = toAdd;
		mySize = source.length();
		myAppends = 0;
		list = myFirst;
		count = 0;
		dex = 0;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		Node toAdd = new Node(dna);
		myLast.next = toAdd;
		myLast = toAdd;
		mySize += dna.length();
		myAppends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		StringBuilder first = new StringBuilder();
		first.append(myFirst.info);
		LinkStrand reverse = new LinkStrand(first.reverse().toString());
		Node current = myFirst.next;
		while(current != null) {
			StringBuilder opposite = new StringBuilder(current.info);
			Node toAdd = new Node(opposite.reverse().toString());
			toAdd.next = myFirst;
			myFirst = toAdd;
			mySize++;
			current = current.next;
		} 
		//WHAT DO I RETURN?
		return reverse;
	}

	@Override
	public String getStats() {
		return String.format("# appends = %d", myAppends);
	}
	
//efficient charAt
	@Override
	public char charAt(int index) {
		if(index >= mySize) {
			throw new IndexOutOfBoundsException();
		}
		if (index < count) {
			count=0;
			dex=0;
			list = myFirst;
		}
		while (count != index) {
			count++;
			dex++;							
			if (dex >= list.info.length()) {
				dex = 0;
				list = list.next;
			}
		}
        return list.info.charAt(dex);
	} 
	
	public String toString() {
		StringBuilder DNA = new StringBuilder();
		Node current = myFirst;
		while (current!= null) {
			DNA.append(current.info);
			current = current.next;
		}
		return DNA.toString();
	}
}
