package app.cnode.utils.completer;

import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.utils.AttributedString;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class MethodNameCompleter implements Completer {

    private final Completer delegate;

    public MethodNameCompleter(Class<?> serviceClass) {
        Set<String> methodNames = new HashSet<>();
        for (Method method : serviceClass.getMethods()) {
            methodNames.add(method.getName());
        }
        // Using a StringsCompleter to delegate the actual completion logic
        this.delegate = new StringsCompleter(methodNames);
    }

    @Override
    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
        delegate.complete(reader, line, candidates);
        for (int i = 0; i < candidates.size(); i++) {
            Candidate original = candidates.get(i);
            candidates.set(i, new Candidate(
                    AttributedString.stripAnsi(original.value()),
                    original.displ(),
                    original.group(),
                    original.descr(),
                    null,
                    original.key(),
                    original.complete()
            ));
        }
    }
}
