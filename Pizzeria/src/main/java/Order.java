public class Order {
    private final int number;
    private final int deliveryTime;
    private State state;

    /**
     *
     * @param number Number created order.
     * @param deliveryTime Time of delivering pizza.
     */
    Order(int number, int deliveryTime) {
        this.number = number;
        this.deliveryTime = deliveryTime;
        this.state = State.PENDING;
    }

    /**
     * @return Time of delivering for this order.
     */
    public int getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * Update state in order
     * @return <code>true</code> if state was updated or <code>false</code> otherwise.
     */
    public boolean updateState() {
        State[] values = State.values();
        int nextState = this.state.ordinal() + 1;
        if (nextState < values.length) {
            this.state = values[nextState];
            return true;
        }
        return false;
    }

    /**
     * Prints state of the order.
     */
    public void printState() {
        System.out.printf("%7d |%11s\n", this.number, this.state.toString());
    }

    /**
     * All kind of states in order
     */
    public enum State {
        PENDING {
            @Override
            public String toString() {
                return "pending";
            }
        },
        COOKING {
            @Override
            public String toString() {
                return "cooking";
            }
        },
        COOKED {
            @Override
            public String toString() {
                return "cooked";
            }
        },
        DELIVERING {
            @Override
            public String toString() {
                return "delivering";
            }
        },
        COMPLETED {
            @Override
            public String toString() {
                return "completed";
            }
        }
    }
}
