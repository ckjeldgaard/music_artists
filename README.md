# MusicArtists
An experimental Android app querying the Last.fm API using various Android tools.

The app consists of two main screens; one for searching music artists and one for displaying artist information.

## Tools

The following tools are used and tested in this app:

 * [Last.fm API](http://www.last.fm/api) - searhing music artists.
 * [Retrofit](https://square.github.io/retrofit/) - to query the Last.fm API
 * [Dagger 2](https://google.github.io/dagger/) - Using dependency injection for Android fragments
 * [Picasso](http://square.github.io/picasso/) - for downloading and caching artist images from Last.fm
 * [JUnit](http://junit.org/junit4/) - for unit testing
 * [Mockito](http://site.mockito.org/) - to create mock objects in unit tests
 * [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - for testing Retrofit HTTP requests and responses
 * [Robolectric](http://robolectric.org/) - for unit testing Activity code
 * [PIT Mutation testing](http://pitest.org/) - for applying mutation testing
 * [gradle-pitest-plugin](https://github.com/koral--/gradle-pitest-plugin) - experimental Gradle plugin to use PIT in Android projects.
