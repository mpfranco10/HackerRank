# Sherlock and the Valid String
To solve this problem I first count the frecuency of each character, so we have something like:

a: 3, b:3, c:3, d:4

Then I count the frecuency of each count. Ideally we would want the same count for each letter. So we would have:

3: 3, 4: 1

Meaning there are 3 characters with count 3, and one character with count 4. 

If there is only one key, it means every character had the same amount, which is valid.

If there is more than two keys, the string is invalid as we cannot remove only one character.

If there are two keys, we want to know which is the minimum key and the maximum. As we have to remove one character, we would remove it from the max key, but it has to appear only once in the string for it to be valid. If the min key is 1 it is also valid as we can remove it. 

