package impl.logic

/**
 * A constraint is a either a fact (i.e. a single predicate) or a rule (i.e. a horn clause).
 */
trait Constraint {
  def head: Predicate
  def body: List[Predicate]

  /**
   * Returns a simplification of the rule where the given predicate `p`, appearing in the body, is assumed to hold.
   */
  def simplify(p: Predicate): Constraint = this match {
    case Constraint.Fact(head) => this
    case Constraint.Rule(head, body) => Constraint.Rule(head, body.filter(p2 => p != p2))
  }
}

object Constraint {

  /**
   * A fact consists of a single predicate.
   */
  case class Fact(head: Predicate.GroundPredicate) extends Constraint {
    def body: List[Predicate] = List.empty
  }

  /**
   * A rule is a horn clause and consists of a single head predicate and a list of body predicates.
   */
  case class Rule(head: Predicate, body: List[Predicate]) extends Constraint

}
