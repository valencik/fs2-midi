## fs2-midi

fs2-midi hopes to offer a nice cross-platform API for interacting with [MIDI][wiki] systems.

It is currently very bare-bones.
You can list your currently connected midi devices on the JVM via the `javax.sound.midi` APIs:

```scala mdoc:silent
import cats.effect.IO
import fs2.midi.{Midi, MidiOutputInfo}

def getOutputs(midi: Midi[IO]): IO[List[MidiOutputInfo]] =
  midi.outputs
```

@:callout(info)
Because this documentation is running in [mdoc][mdoc], we need an implicit `IORuntime` to run our `IO` programs and get a value.
In application code you would typically construct your whole program in `IO` and use `IOApp` to run it.

```scala mdoc:silent
import cats.effect.unsafe.IORuntime
implicit val runtime: IORuntime = IORuntime.global
```

And now we can manually run `IO` programs to get a value within mdoc via `unsafeRunSync()` which again, you wouldn't do in application code.
@:@

```scala mdoc
Midi.resource[IO].use(getOutputs).unsafeRunSync()
```

Yupp, the JVM ships with two virtual MIDI devices, a synth (Gervill) and a sequencer!

Welp, that's all for now.
Up next we'll send some output to one of these devices, or your own real physical MIDI device!

[mdoc]: https://scalameta.org/mdoc/
[wiki]: https://en.wikipedia.org/wiki/MIDI
