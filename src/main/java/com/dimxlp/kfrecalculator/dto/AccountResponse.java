package com.dimxlp.kfrecalculator.dto;

import java.util.List;

public record AccountResponse(String principal, String email, List<String> roles, String status) {}
