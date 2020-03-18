package refactoredMethods;

public class ReplaceTypeCodeWIthClassRefactored {
    static class Person {

        enum BloodGroup {O, A, B, AB}

        private BloodGroup _bloodGroup;

        public Person(BloodGroup bloodGroup) {
            _bloodGroup = bloodGroup;
        }

        public void setBloodGroup(BloodGroup arg) {
            _bloodGroup = arg;
        }

        public BloodGroup getBloodGroup() {
            return _bloodGroup;
        }
    }

}
