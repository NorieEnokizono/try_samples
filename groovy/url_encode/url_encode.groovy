@Grab('commons-codec:commons-codec:1.7')

str = ';/?:@=&% $-_.+!*\'"(),{}|\\^~[]'

// %3B%2F%3F%3A%40%3D%26%25+%24-_.%2B%21*%27%22%28%29%2C%7B%7D%7C%5C%5E%7E%5B%5D
println URLEncoder.encode(str)

// %3B%2F%3F%3A%40%3D%26%25+%24-_.%2B%21*%27%22%28%29%2C%7B%7D%7C%5C%5E%7E%5B%5D
println new org.apache.commons.codec.net.URLCodec().encode(str)
