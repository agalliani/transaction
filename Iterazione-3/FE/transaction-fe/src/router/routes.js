/**
 * Views import
 */
import LandingPage from "../views/LandingPage";
import ProposalPage from "../views/ProposalPage";
import TransactionPage from "../views/TransactionPage";

const routes = [
  {
    path: "/",
    name: "landing-page",
    component: LandingPage,
  },
  {
    path: "/proposals",
    name: "proposals",
    component: ProposalPage,
  },
  {
    path: "/transactions",
    name: "transactions",
    component: TransactionPage,
  },
];

export default routes;
