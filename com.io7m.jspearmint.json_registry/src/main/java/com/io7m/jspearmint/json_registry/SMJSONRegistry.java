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

package com.io7m.jspearmint.json_registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigInteger;
import java.util.List;

/**
 * The root registry grammar.
 */

// CHECKSTYLE:OFF

@JsonDeserialize
public final class SMJSONRegistry
{
  @JsonProperty(
    required = true,
    value = "copyright"
  )
  public List<String> copyright = List.of();

  @JsonProperty(
    required = true,
    value = "magic_number"
  )
  public String magicNumber;

  @JsonProperty(
    required = true,
    value = "major_version"
  )
  public BigInteger majorVersion = BigInteger.ZERO;

  @JsonProperty(
    required = true,
    value = "minor_version"
  )
  public BigInteger minorVersion = BigInteger.ZERO;

  @JsonProperty(
    required = true,
    value = "revision"
  )
  public BigInteger revision = BigInteger.ZERO;

  @JsonProperty(
    required = true,
    value = "instruction_printing_class"
  )
  public List<SMJSONInstructionClass> instructionClasses;

  @JsonProperty(
    required = true,
    value = "instructions"
  )
  public List<SMJSONInstruction> instructions;

  @JsonProperty(
    required = true,
    value = "operand_kinds"
  )
  public List<SMJSONOperandKind> operandKinds;


  public SMJSONRegistry()
  {

  }
}
