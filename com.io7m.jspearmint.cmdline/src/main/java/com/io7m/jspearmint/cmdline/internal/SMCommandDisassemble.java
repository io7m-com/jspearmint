/*
 * Copyright © 2020 Mark Raynsford <code@io7m.com> http://io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jspearmint.cmdline.internal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.io7m.claypot.core.CLPAbstractCommand;
import com.io7m.claypot.core.CLPCommandContextType;
import com.io7m.jspearmint.disassembly.api.SMDisassemblerConfiguration;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Disassemble a file.
 */

@Parameters(commandDescription = "Disassemble SPIR-V bytecode")
public final class SMCommandDisassemble extends CLPAbstractCommand
{
  @Parameter(
    description = "The input file.",
    names = "--inputFile",
    required = true
  )
  private Path inputFile;

  /**
   * Construct a command.
   *
   * @param context The context
   */

  public SMCommandDisassemble(
    final CLPCommandContextType context)
  {
    super(context);
  }

  @Override
  public String name()
  {
    return "disassemble";
  }

  @Override
  public Status executeActual()
    throws Exception
  {
    final var parsers =
      SMServices.findParsers();
    final var disassemblers =
      SMServices.findDisassemblers();

    final var configuration =
      SMDisassemblerConfiguration.builder()
        .build();

    try (var stream = Files.newInputStream(this.inputFile)) {
      try (var parser = parsers.create(this.inputFile.toUri(), stream)) {
        try (var disassembler = disassemblers.create()) {
          final var instructions = parser.parseAllInstructions();
          disassembler.disassemble(
            configuration,
            parser.header(),
            instructions,
            System.out
          );
        }
      }
    }

    System.out.flush();
    return Status.SUCCESS;
  }
}
