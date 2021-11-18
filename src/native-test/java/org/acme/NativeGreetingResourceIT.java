package org.acme;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
class NativePetControllerIT extends PetControllerTest {

    // Execute the same tests but in native mode.
}