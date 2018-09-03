package cluster.hierarchical;
    public class ClusterPair {
        Cluster cluster1;
        Cluster cluster2;
        double distance;

        public ClusterPair(Cluster cluster1, Cluster cluster2) {
            this.cluster1 = cluster1;
            this.cluster2 = cluster2;
            distance = cluster1.getCentriod().getDistance(cluster2.getCentriod());
        }

        // //cluster pair
        // @Override
        // public int compareTo(ClusterPair o) {
        // if (distance - o.distance > 0){
        // return +1;
        // }if (distance - o.distance < 0){
        // return -1;
        // }else{
        // return this.hashCode()-o.hashCode();
        // }
        // }
        // @Override
        // public int hashCode() {
        // if(cluster1.hashCode() < cluster2.hashCode()){
        // return new StringBuffer()
        // .append(String.valueOf(cluster1.hashCode()))
        // .append(String.valueOf(cluster2.hashCode())).hashCode();
        // }else{
        // return new StringBuffer()
        // .append(String.valueOf(cluster2.hashCode()))
        // .append(String.valueOf(cluster1.hashCode())).hashCode();
        // }
        // }
        public Cluster merge() {
            return cluster1.merge(cluster2);
        }

        @Override
        public String toString() {
            return new StringBuffer().append(cluster1).append(",").append(cluster2).append("=").append(distance)
                    .toString();

        }
        // @Override
        // public boolean equals(Object obj) {
        // return hashCode() == obj.hashCode();
        // }
    }