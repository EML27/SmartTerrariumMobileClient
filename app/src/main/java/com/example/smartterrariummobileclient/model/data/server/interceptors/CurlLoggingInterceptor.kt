/*
 * Copyright (C) 2016 Jeff Gilfelt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.smartterrariummobileclient.model.data.server.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor.Logger
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

class CurlLoggingInterceptor @JvmOverloads constructor(private val logger: Logger = Logger.DEFAULT) : Interceptor {
    private var curlOptions: String? = null

    /** Set any additional curl command options (see 'curl --help').  */
    fun setCurlOptions(curlOptions: String) {
        this.curlOptions = curlOptions
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var compressed = false

        var curlCmd = "curl"
        if (curlOptions != null) {
            curlCmd += " " + curlOptions!!
        }
        curlCmd += " -X " + request.method()

        val headers = request.headers()
        var i = 0
        val count = headers.size()
        while (i < count) {
            val name = headers.name(i)
            val value = headers.value(i)
            if ("Accept-Encoding".equals(name, ignoreCase = true) && "gzip".equals(value, ignoreCase = true)) {
                compressed = true
            }
            curlCmd += " -H \"$name: $value\""
            i++
        }

        val requestBody = request.body()
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            var charset = UTF8
            val contentType = requestBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
            }
            // try to keep to a single line and use a subshell to preserve any line breaks
            curlCmd += " --data $'" + buffer.readString(charset).replace("\n", "\\n") + "'"
        }

        curlCmd += (if (compressed) " --compressed " else " ") + request.url()

        logger.log("╭--- cURL (" + request.url() + ")")
        logger.log(curlCmd)
        logger.log("╰--- (ic_copy_non_active and paste the above line to a terminal)")

        return chain.proceed(request)
    }

    companion object {

        private val UTF8 = Charset.forName("UTF-8")
    }

}