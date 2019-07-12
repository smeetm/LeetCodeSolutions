package com.leetcode.solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * 
 
 @author Administrator
Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 
Example 3:
Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

Example 4:
Input: "/a/./b/../../c/"
Output: "/c"

Example 5:
Input: "/a/../../b/../c//.//"
Output: "/c"

Example 6:
Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
 *
 */

public class Question71 {

	public String simplifyPath(String path) {

		Stack<String> stack = new Stack<String>();
		String output = "";

		List<String> alldirs = getCurrDirNames(path);

		for (String s : alldirs) {
			if (s.equals("..")) {
				if (stack.size() > 0) {
					stack.pop();
				}
			} else if (s.length() > 0 && !s.equals(".")) {
				stack.push(s);
			}
		}

		while (!stack.isEmpty()) {
			output = "/" + stack.pop() + output;
		}

		if (output.length() == 0)
			output = "/";
		return output;
	}

	// Returns array for dir names 
	public List<String> getCurrDirNames(String path) {
		List<String> dirNames = new LinkedList<String>();
		int lastSlash = 0;
		int i = 0;

		while (i < path.length()) {
			if (path.charAt(i) == '/') {
				String currDirName = "";
				if (i == 0) {
					currDirName = path.substring(lastSlash, i);
				} else {
					currDirName = path.substring(lastSlash + 1, i);
				}

				dirNames.add(currDirName);
				lastSlash = i;
			}

			i++;
		}

		if (i == path.length() && path.charAt(i - 1) != '/') {
			String currDirName = path.substring(lastSlash + 1, i);
			dirNames.add(currDirName);
		}

		return dirNames;
	}

}
