/*
 * Copyright 2026 fff
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fs2
package midi

import cats.Monad
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.std.Console
import cats.syntax.all._

object ListMidiDevicesApp extends IOApp.Simple {
  def program[F[_]: Midi: Console: Monad]: F[Unit] =
    for {
      outputs <- Midi[F].outputs
      _ <- Console[F].println(s"Found ${outputs.size} output device(s):")
      _ <- outputs.traverse_(info => Console[F].println(s"  [${info.index}] ${info.name}"))
    } yield ()

  val run = Midi.resource.use(implicit midi => program[IO])
}
