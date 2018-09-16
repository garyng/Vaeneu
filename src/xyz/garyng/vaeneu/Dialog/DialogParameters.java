package xyz.garyng.vaeneu.Dialog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DialogParameters
{
    @Builder.Default
    private String title = "Are you sure?";
    @Builder.Default
    private String body = "Default body text";
    @Builder.Default
    private String acceptedText = "YES";
    @Builder.Default
    private String rejectedText = "NO";
    @Builder.Default
    private Runnable onAccepted = () ->
    {
    };
    @Builder.Default
    private Runnable onRejected = () ->
    {
    };
}
